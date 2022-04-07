package SearchEngine;

import java.util.Scanner;

public class MainSearchEngine {
	
	static int mainInput,input;
	static String url="";
	static Scanner read = new Scanner(System.in);
	
	public static void crawl(int input) {
		while(input!=0) {
			switch(input) {

			case 1:
				System.out.println("\nEnter/paste the complete URL:");
				url = read.next();
				System.out.println("\nPlease wait...\nCrawling web pages may take few minutes...");
				Crawler.crawler(url);
				System.out.println("\nWeb pages crawled successfully!");
				System.out.println();
				break;
				
			case 2:
				for(String file: Crawler.fetchURLList())
					System.out.println(file);
				break;
				
			case 3:
				System.out.println("\n\n\t\t------------------------");
				System.out.println("\t\t|  S E A R C H  W O R D  |");
				System.out.println("\t\t------------------------");
				SearchWord.searchWord();
				break;
				
			case 4:
				break;
				
			default:
				System.out.println("invalid input.. Please enter valid input..");
			}
			System.out.println("\n1. Crawl different pages\t2. Display all the crawled pages\t3. Search\t0. Exit");
			input = read.nextInt();
		}
		System.out.println("\nWeb Crawler closed!");
	}
	
	public static void main(String[] args) {
		System.out.println("\t\t------------------------------");
		System.out.println("\t\t|  S E A R C H  E N G I N E  |");
		System.out.println("\t\t------------------------------");
		System.out.println("\nSelect the option below:");
		System.out.println("1. Crawl Pages\t\t2. Search\t3. Exit");
		mainInput = read.nextInt();
		while(mainInput!=0) {
			switch(mainInput) {
			
			case 1:
				System.out.println("\n\n\t\t--------------------------");
				System.out.println("\t\t|  W E B  C R A W L E R  |");
				System.out.println("\t\t--------------------------");
				crawl(mainInput);
				break;
				
			case 2:
				System.out.println("\n\n\t\t------------------------");
				System.out.println("\t\t|  W E B  S E A R C H  |");
				System.out.println("\t\t------------------------");
				SearchWord.searchWord();
				System.out.println("\n1. Search Again\t\t0. Exit");
				int in = read.nextInt();
				while(in!=0) {
					switch(in) {
					
					case 1:
						SearchWord.searchWord();
						break;
						
					case 2:
						break;
						
					default:
						System.out.println("invalid input.. Please enter valid input..");
						
					}
					
					System.out.println("\n1. Search Again\t\t0. Exit");
					in = read.nextInt();
				}
				System.out.println("Web Search closed!");
				break;
				
			default:
				System.out.println("invalid input.. Please enter valid input..");
			}
			System.out.println("\n1. Crawl Pages\t\t2. Search\t0. Exit");	
			mainInput = read.nextInt();
		}
		System.out.println("\nSearch Engine closed!");
	}
}
