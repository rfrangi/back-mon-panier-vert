package rfi.monpaniervert.enums;

public enum ErrorException {
	EMAIL_EXISTING,
	PSEUDO_EXISTING,

	NOT_FOUND,
	
	ERROR_LOGIN, // Cas ou un admin à bloqué cet utilisateur

	DISABLED_ACCOUNT, // Cas ou un admin à bloqué cet utilisateur
	LOCK_ACCOUNT, // Cas ou l'utilisateur se bloque avec trop de saisie de MDP

	TOKEN_RESET_PASSWORD_INVALID, // Cas ou le token reset password est invalid ou expiré

	CODE_VIP_NOT_FOUND,
	CODE_VIP_DISABLED,
	CODE_VIP_ALREADY_USED,
	
	MDP_INVALID // Changement de MDP ou l'ancien mdp n'est pas bon
}
