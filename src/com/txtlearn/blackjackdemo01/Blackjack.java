package com.txtlearn.blackjackdemo01;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		//Welcome Message
		System.out.println("Tervetuloa Blackjack peliin!");
		
		//Create our playing deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		//Create a Deck For The Player
		Deck playerDeck = new Deck();
		
		//Create a Deck For The Dealer
		Deck dealerDeck = new Deck();
		
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		
		//Game Loop
		while(playerMoney > 0) {
			//Play On!
			//Take Bets
			System.out.println("Saldosi on: €" + playerMoney + " , Aseta panos");
			double playerBet = userInput.nextDouble();
			if(playerBet > playerMoney) {
				System.out.println("Et voi panostaa yli varojesi. Aloitetaan alusta.");
				break;
			}
			
			boolean endRound = false;
			
			//Start Dealing
			//Player Gets Two Cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//Dealer Gets Two Cards
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true) {
				System.out.println("Sinun kätesi:");
				System.out.print(playerDeck.toString());
				System.out.println(", Korttiesi summa: " + playerDeck.cardsValue());
				
				//Display Dealers Hand
				System.out.println("Jakajan käsi: " + dealerDeck.getCard(0).toString() + " ja [Hidden]");
				
				//What Does The Player Want To Do?
				System.out.println("Haluatko kortin? (1)Hit (2)Stand");
				int response = userInput.nextInt();
				
				//They Hit
				if(response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("Sait: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//BUST If > 21
					if(playerDeck.cardsValue() > 21) {
						System.out.println("Yli. Korttien summa: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				if(response==2) {
					break;
				}
			}
			
			//Reveal dealer
			System.out.println("Jakajan käsi: " + dealerDeck.toString());
			//See If dealer wins.
			if ((dealerDeck.cardsValue()>=17) &&  (dealerDeck.cardsValue()>playerDeck.cardsValue())&& endRound==false) {
				System.out.println("Jakaja voittaa!");
				playerMoney -= playerBet;
				endRound = true;
			}
			//Dealer Draws at 16, stand at 17
			while((dealerDeck.cardsValue() < 17) && endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("Jakaja saa kortin: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//Display total value for dealer
			System.out.println("Jakajan korttien summa: " + dealerDeck.cardsValue());
			//Dealer Bust??
			if((dealerDeck.cardsValue() > 21)&& endRound == false) {
				System.out.println("Jakajan käsi meni yli. Voitit!");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//Determine push??
			if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("Tasapeli");
				endRound = true;
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("Voitit käden!");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) {
				System.out.println("Hävisit käden.");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("Käsi ohi.");
			
		}
		
		System.out.println("Ei katetta! :(");
		
	}

}
