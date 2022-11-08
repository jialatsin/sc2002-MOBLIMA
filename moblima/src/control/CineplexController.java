package control;

import entity.Cineplex;
/**
 * It Represents the Cineplex Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class CineplexController extends DatabaseController<Cineplex> {
	/** 
     * File path name of cineplex database file to access. 
     */
	public CineplexController() {
        super(MainController.FILEPATH_CINEPLEX);
    }
}
