package rfi.monpaniervert.services.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rfi.monpaniervert.dto.CommandeClientDTO;
import rfi.monpaniervert.dto.ProduitCommandeDTO;
import rfi.monpaniervert.dto.TdbCommandeDTO;
import rfi.monpaniervert.exceptions.NotAccessException;
import rfi.monpaniervert.exceptions.NotFoundException;
import rfi.monpaniervert.exceptions.QuantiteNonDispoException;
import rfi.monpaniervert.managers.CommandeClientManager;
import rfi.monpaniervert.managers.CommandeCompagnieManager;
import rfi.monpaniervert.managers.ProduitCommandeManager;
import rfi.monpaniervert.managers.ProduitManager;
import rfi.monpaniervert.mappers.CommandeClientMapper;
import rfi.monpaniervert.models.CommandeClient;
import rfi.monpaniervert.models.CommandeCompagnie;
import rfi.monpaniervert.models.ProduitCommande;
import rfi.monpaniervert.security.services.UserDetailsImpl;
import rfi.monpaniervert.services.CommandeClientService;

@Service
public class CommandeClientServiceImpl implements CommandeClientService {

	//@Autowired private CompagnieManager compagnieManager;
	
	@Autowired private ProduitManager produitManager;

	
	@Autowired private CommandeClientMapper commandeClientMapper;
	
	@Autowired private CommandeClientManager commandeClientManager;
	@Autowired private CommandeCompagnieManager commandeCompagnieManager;
	@Autowired private ProduitCommandeManager produitCommandeManager;

  //  private static final DecimalFormat df = new DecimalFormat("0.00");

    
	@Override
	public CommandeClient post(CommandeClientDTO commandeClientDTO) {
		
		commandeClientDTO.setCreationDate(new Date());
		final List<ProduitCommandeDTO> produitsCommande = this.getListProduitCommande(commandeClientDTO);
		this.checkQuantiteDispo(produitsCommande);
		
		
		/** SET MONTANT **/
		final Double montantTotalProduit = this.getMontantTotal(produitsCommande);
		commandeClientDTO.setMontant(montantTotalProduit);
		
		/** MAJ STOCK PRODUIT **/
		this.produitManager.updateQuantiteProduit(produitsCommande);
		
		/** REMOVE ID ADRESSE **/
		commandeClientDTO.getAdresseFacturation().setIdAdresse(null);
		commandeClientDTO.getAdresseLivraison().setIdAdresse(null);

		final CommandeClient result = this.saveCommande(commandeClientDTO);
		return result;
	
	}
	
	private CommandeClient saveCommande(CommandeClientDTO commandeClientDTO) {
		final CommandeClient entity = commandeClientMapper.toEntity(commandeClientDTO);
		final Set<CommandeCompagnie> cmdCompagnie = entity.getCommandesCompagnie();
		entity.setCommandesCompagnie(new HashSet<CommandeCompagnie>());
		
		final CommandeClient cmdClient = commandeClientManager.save(entity);
		
		cmdCompagnie.stream().forEach(val -> {
			
			val.setCommandeClient(cmdClient);
			Set<ProduitCommande> produitCmdByComp = val.getProduitsCommande();
			val.setProduitsCommande(new HashSet<ProduitCommande>());
			final CommandeCompagnie cmdCompagnieEntity = this.commandeCompagnieManager.save(val);
			cmdClient.getCommandesCompagnie().add(cmdCompagnieEntity);
			
			produitCmdByComp.stream().forEach(produitCmd -> {
				produitCmd.setCommandeCompagnie(cmdCompagnieEntity);
				final ProduitCommande produitCmdEntity = produitCommandeManager.save(produitCmd);
				cmdCompagnieEntity.getProduitsCommande().add(produitCmdEntity);
			});
		});
		return cmdClient;
	}
	
	
	private List<ProduitCommandeDTO> getListProduitCommande(CommandeClientDTO commandeClientDTO) {
		final List<ProduitCommandeDTO> produitsCommande = new ArrayList<ProduitCommandeDTO>();
		commandeClientDTO.getCommandesCompagnie().stream().forEach(cmdCompagnie -> {
			produitsCommande.addAll(cmdCompagnie.getProduitsCommande());
		});
		return produitsCommande;
	}
	
	private Double getMontantTotal(List<ProduitCommandeDTO> produitsCommande) {
		return produitsCommande.stream().map(val -> val.getTarif() * val.getQuantiteCommande()).mapToDouble(Double::doubleValue).sum();
	}
	
	private void checkQuantiteDispo(List<ProduitCommandeDTO> produits) {
		
		final List<Long> result = new ArrayList<Long>();
		for (ProduitCommandeDTO produitCommande : produits) {
			final Long idProduit = produitCommande.getIdProduit();
			final Long quantiteDispo = this.produitManager.getQuantiteById(idProduit);
	 
			if(quantiteDispo != null && quantiteDispo < produitCommande.getQuantiteCommande()) {
				result.add(idProduit);
			}
		}
		if(result.size() > 0) {
			throw new QuantiteNonDispoException(String.join(",", result.stream().map(val -> val.toString()).collect(Collectors.toList())));
		}
	}

	@Override
	public Page<CommandeClientDTO> findByUser(TdbCommandeDTO tdbCommandeDTO, Pageable pagination) {
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		final Long userId = securityUser.getId();
			tdbCommandeDTO.setUserId(userId);
			tdbCommandeDTO.setSiteId(null);
			tdbCommandeDTO.setCompagnieId(null);
			return this.commandeClientManager.find(tdbCommandeDTO, pagination);
	}

	@Override
	public CommandeClient getById(Long id) {
		final CommandeClient cmdClient =  this.commandeClientManager.getById(id);
		final UserDetailsImpl securityUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		if (!cmdClient.getUserId().equals(securityUser.getId())) {
			throw new NotAccessException(MessageFormat.format("User with id: {0} couldn''t be access cmd {1}", id, cmdClient.getId()));
		}
		
		return cmdClient;
	}

	@Override
	public Page<CommandeClientDTO> findByCompagnie(TdbCommandeDTO tdbCommandeDTO, Pageable pagination) {
		tdbCommandeDTO.setUserId(null);
		tdbCommandeDTO.setSiteId(null);
		return this.commandeClientManager.find(tdbCommandeDTO, pagination);

	}

	@Override
	public Page<CommandeClientDTO> findBySite(TdbCommandeDTO tdbCommandeDTO, Pageable pagination) {
		tdbCommandeDTO.setUserId(null);
		tdbCommandeDTO.setCompagnieId(null);
		return this.commandeClientManager.find(tdbCommandeDTO, pagination);
	}
	
}
