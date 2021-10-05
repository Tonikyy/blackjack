package com.txtlearn.blackjackdemo01;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	//instance vars
	private ArrayList<Card> cards;
	
	//construct
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
		//Generate Card
		for(Suit cardSuit : Suit.values()) {
			for(Value cardValue : Value.values()) {
				//Add a new card to deck
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}
	
	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		//Use Random
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for(int i = 0; i < originalSize; i++) {
			//Generate Random Index
			randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			//Remove From Original Deck
			this.cards.remove(randomCardIndex);
		}
		
		this.cards = tmpDeck;
		
	}
	
	public String toString() {
		String cardListOutput = "";
		for(Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
	
		}
		return cardListOutput;
	}
	
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	//Draws From The Deck
	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	
	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		
		//Put Cards Into moveTo deck
		for(int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
		
	}
	
	
	//Return Total Value Of Cards In Deck
	public int cardsValue() {
		int totalValue = 0;
		int aces = 0;
		
		for(Card aCard : this.cards) {
			switch(aCard.getValue()) {
			case KAKSI: totalValue += 2; break;
			case KOLME: totalValue += 3; break;
			case NELJÄ: totalValue += 4; break;
			case VIISI: totalValue += 5; break;
			case KUUSI: totalValue += 6; break;
			case SEITSEMÄN: totalValue += 7; break;
			case KAHDEKSAN: totalValue += 8; break;
			case YHDEKSÄN: totalValue += 9; break;
			case KYMMENEN: totalValue += 10; break;
			case JÄTKÄ: totalValue += 10; break;
			case KUNINGATAR: totalValue += 10; break;
			case KUNINGAS: totalValue += 10; break;
			case ÄSSÄ: aces += 1; break;
			}
		}
		
		for(int i = 0; i < aces; i++) {
			if(totalValue > 10) {
				totalValue += 1;
			}
			else {
				totalValue += 11;
			}
		}
		
		return totalValue;
		
		
	}
	
	
	
	
	
}
