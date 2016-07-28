package com.stayinspired;

public class Question {

	private int ID;
	private String QUESTION;
	private String CORRECT_ANSWER;
	private String OPTIONB;
	private String OPTIONC;
	private String OPTIOND;

	public Question() {
		ID = 0;
		QUESTION = "";
		CORRECT_ANSWER = "";
		OPTIOND = "";
		OPTIONB = "";
		OPTIONC = "";
	}

	public Question(String qUESTION, String cORRECT_ANSWER, String oPTIONB,
			String oPTIONC, String oPTIOND) {

		QUESTION = qUESTION;
		CORRECT_ANSWER = cORRECT_ANSWER;
		OPTIONB = oPTIONB;
		OPTIONC = oPTIONC;
		OPTIOND = oPTIOND;

	}

	public int getID() {
		return ID;
	}

	public String getQUESTION() {
		return QUESTION;
	}

	public String getOPTB() {
		return OPTIONB;
	}

	public String getOPTC() {
		return OPTIONC;
	}

	public String getOPTD() {
		return OPTIOND;
	}

	public String getCorrectAnswer() {
		return CORRECT_ANSWER;
	}

	public void setID(int id) {
		ID = id;
	}

	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}

	public void setOPTB(String oPTB) {
		OPTIONB = oPTB;
	}

	public void setOPTC(String oPTC) {
		OPTIONC = oPTC;
	}

	public void setOPTD(String oPTD) {
		OPTIOND = oPTD;
	}

	public void setCorrectAnswer(String aNSWER) {
		CORRECT_ANSWER = aNSWER;
	}

}