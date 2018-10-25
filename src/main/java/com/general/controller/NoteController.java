package com.general.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.NoteDao;
import com.general.dao.RecetteDao;
import com.general.dao.RecetteIngredientDao;
import com.general.model.Note;
import com.general.model.Recette;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;


@Controller
@RestController
@RequestMapping(value = "/Note")
public class NoteController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	NoteDao noteDao;
	@Autowired
	RecetteDao recetteDao;
	@Autowired
	RecetteIngredientDao recetteIngredientDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/GeListAllNotes", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Note> GeListAllNotes()
	{
		List<Note> notes = noteDao.findAll();
		return notes;
	}
	
	@RequestMapping(value = "/GetListNotesByRecette/{idRecette}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Note> GetListNotesByRecette(@PathVariable int idRecette)
	{
		Recette recette=new Recette();
		recette.setIdRecette(idRecette);
		List<Note> notes = noteDao.findAllByrecette(recette);
		return notes;
	}
	
	@RequestMapping(value = "/GetListNotesByUserId/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Note> GetListNotesByUserId(@PathVariable int id)
	{
		User user = new User();
		user.setIdUser(id);
		List<Note> notes = noteDao.findAllByuser(user);
		return notes;
	}
	
	@RequestMapping(value = "/AddNote", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Note AddNote(@RequestBody Note note)
	{
		note=noteDao.saveAndFlush(note);
		return note;
	}
	
	@RequestMapping(value = "/UpdateNote", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Note UpdateNote(@RequestBody Note note)
	{
		note=noteDao.saveAndFlush(note);
		return note;
	}
	

}
