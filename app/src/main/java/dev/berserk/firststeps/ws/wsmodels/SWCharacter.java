package dev.berserk.firststeps.ws.wsmodels;

import java.util.List;

public class SWCharacter{
	private List<String> films;
	private String homeworld;
	private String gender;
	private String skinColor;
	private String edited;
	private String created;
	private String mass;
	private List<Object> vehicles;
	private String url;
	private String hairColor;
	private String birthYear;
	private String eyeColor;
	private List<String> species;
	private List<String> starships;
	private String name;
	private String height;

	public void setFilms(List<String> films){
		this.films = films;
	}

	public List<String> getFilms(){
		return films;
	}

	public void setHomeworld(String homeworld){
		this.homeworld = homeworld;
	}

	public String getHomeworld(){
		return homeworld;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSkinColor(String skinColor){
		this.skinColor = skinColor;
	}

	public String getSkinColor(){
		return skinColor;
	}

	public void setEdited(String edited){
		this.edited = edited;
	}

	public String getEdited(){
		return edited;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setMass(String mass){
		this.mass = mass;
	}

	public String getMass(){
		return mass;
	}

	public void setVehicles(List<Object> vehicles){
		this.vehicles = vehicles;
	}

	public List<Object> getVehicles(){
		return vehicles;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHairColor(String hairColor){
		this.hairColor = hairColor;
	}

	public String getHairColor(){
		return hairColor;
	}

	public void setBirthYear(String birthYear){
		this.birthYear = birthYear;
	}

	public String getBirthYear(){
		return birthYear;
	}

	public void setEyeColor(String eyeColor){
		this.eyeColor = eyeColor;
	}

	public String getEyeColor(){
		return eyeColor;
	}

	public void setSpecies(List<String> species){
		this.species = species;
	}

	public List<String> getSpecies(){
		return species;
	}

	public void setStarships(List<String> starships){
		this.starships = starships;
	}

	public List<String> getStarships(){
		return starships;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"SWCharacter{" + 
			"films = '" + films + '\'' + 
			",homeworld = '" + homeworld + '\'' + 
			",gender = '" + gender + '\'' + 
			",skin_color = '" + skinColor + '\'' + 
			",edited = '" + edited + '\'' + 
			",created = '" + created + '\'' + 
			",mass = '" + mass + '\'' + 
			",vehicles = '" + vehicles + '\'' + 
			",url = '" + url + '\'' + 
			",hair_color = '" + hairColor + '\'' + 
			",birth_year = '" + birthYear + '\'' + 
			",eye_color = '" + eyeColor + '\'' + 
			",species = '" + species + '\'' + 
			",starships = '" + starships + '\'' + 
			",name = '" + name + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}