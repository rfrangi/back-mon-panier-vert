package rfi.monpaniervert.enums;

public enum ErrorException {
	EMAIL_EXISTING,
	PSEUDO_EXISTING,

	NOT_FOUND,
	RESSOURCE_NOT_ACCESS,
	
	ERROR_LOGIN, // Cas ou un admin à bloqué cet utilisateur

	DISABLED_ACCOUNT, // Cas ou un admin à bloqué cet utilisateur
	LOCK_ACCOUNT, // Cas ou l'utilisateur se bloque avec trop de saisie de MDP

	TOKEN_RESET_PASSWORD_INVALID, // Cas ou le token reset password est invalid ou expiré

	
	QUANTITE_NON_DISPO, // Cas ou la quantite commande n'est pas dispo

	
	MDP_INVALID // Changement de MDP ou l'ancien mdp n'est pas bon
}
