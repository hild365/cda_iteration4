package modele;

/**
 * La classe InputException est une sous-classe de Exception.
 * Elle est utilisée pour gérer les exceptions spécifiques à l'entrée de l'utilisateur dans le jeu.
 */
public class InputException extends Exception {
    private final String message;

    /**
     * Constructeur de la classe InputException.
     *
     * @param message Le message d'erreur à afficher lorsque l'exception est levée.
     */
    public InputException(String message) {
        this.message = message;
    }

    /**
     * Récupère le message d'erreur de l'exception.
     *
     * @return Le message d'erreur de l'exception.
     */
    public String getMessage() {
        return message;
    }
}