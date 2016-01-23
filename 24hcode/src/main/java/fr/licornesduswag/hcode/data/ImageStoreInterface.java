package fr.licornesduswag.hcode.data;

import java.io.InputStream;

/**
 * Une interface qui représente un store d'images
 * @author Kelian Duval (Spritix)
 */
public interface ImageStoreInterface {
    /**
     * Ajoute une image au store
     * @param nom Le nom (avec extension) de l'image qui va Ãªtre chargÃ©e
     * @param is Le flux Ã  partir duquel charger l'image
     */
    public void addImage(String nom, InputStream is);
}
