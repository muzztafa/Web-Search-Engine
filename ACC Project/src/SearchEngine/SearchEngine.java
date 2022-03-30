package SearchEngine;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;
public class SearchEngine {
	
	private static Scanner sc = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		SearchEngine SearchEngine = new SearchEngine();
		System.out.println("Welcome to Web Search Engine\n");
		System.out.println("*************************************************************************\n");
		System.out.println("Enter the URL you would like us to Crawl");
		System.out.println("\n*************************************************************************\n");
		String URL = sc.next();
		URL = "https://"+URL+"/";
		String choose = SearchEngine.searchWord(URL);	
		
		System.out.println("\n*************************************************************************\n");
		System.out.println("               :) This is the END of the Demo :)               ");
		System.out.println("\n*************************************************************************\n");
		
	}

	private String searchWord(String URL) {
		
		if(!isValid(URL)) {
			 System.out.println("Enterd URL " + URL + " isn't valid");
			 System.out.println("Please try again....\n");
			 return "y";
		}
		
		System.out.println("Enterd URL " + URL + " is valid\n");
		
		System.out.println("Crawling has begun...");
		Crawler.Crawler(URL); 						//crawling the URL
		System.out.println("Crawling has finished...");

		// Hash table is used instead of Hash Map as it don't allow null value in insertion
		Hashtable<String, Integer> FileList = new Hashtable<String, Integer>();
		
		String choice = "y";
		do {
			System.out.println("\n*************************************************************************");
			System.out.println("\n                     Enter a word you want to search                     ");
			System.out.println("\n*************************************************************************");
			String wordToSearch = sc.next();
			System.out.println("\n*************************************************************************");
			int WordFrequency = 0;
			int TotFiles = 0;
			FileList.clear();
			try {
				System.out.println("\nSearching...");
				File Files = new File(Path.txtDirectoryPath);

				File[] ArrayofFiles = Files.listFiles();

				for (int i = 0; i < ArrayofFiles.length; i++) {

					In data = new In(ArrayofFiles[i].getAbsolutePath());

					String txt = data.readAll();
					data.close();
					Pattern p = Pattern.compile("::");
					String[] file_name = p.split(txt);
					WordFrequency = SearchWord.wordSearch(txt, wordToSearch.toLowerCase(), file_name[0]); // search word in txt files

					if (WordFrequency != 0) {
						FileList.put(file_name[0], WordFrequency);
						TotFiles++;
					}
					
				}

				if(TotFiles>0) {
				System.out.println("\nTotal Number of Files containing word : " + wordToSearch + " is : " + TotFiles);
				}else {
					System.out.println("\n File not found! containing word : "+ wordToSearch);
					SearchWord.suggestAltWord(wordToSearch.toLowerCase()); // suggest another word if entered word not found
				}

				SearchWord.rankFiles(FileList, TotFiles); 				   //rank the files based on frequency of word count
				

			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
			System.out.println("\n Do you want return to search another word(y/n)?");
			choice = sc.next();
		} while (choice.equals("y"));
		
		deleteFiles();					// delete the files created if the user do not want to search any other words and want to start with new URL
		
		System.out.println("\n Do you want return to main menu(y/n)?");   // returns to the main menu to choose from new/static URL or exit the code. 
		return sc.next();
	}

	// deletes all files created while crawling and word search.
	private void deleteFiles() {
		File files = new File(Path.txtDirectoryPath);
		File[] ArrayofFiles = files.listFiles();

		for (int i = 0; i < ArrayofFiles.length; i++) {
			ArrayofFiles[i].delete();
		}
		
		File HTMLFiles= new File(Path.htmlDirectoryPath);
		File[] fileArrayhtml = HTMLFiles.listFiles();

		for (int i = 0; i < fileArrayhtml.length; i++) {
			
			fileArrayhtml[i].delete();
		}
	}
	
	/**
	 * It will validate URL entered by user with DNS
	 * @param url
	 * @return
	 */
	public boolean isValid(String URL)
    {
        /* Try creating a valid URL */
        try {
        	System.out.println("Validating URL...");
        	URL obj = new URL(URL);
            HttpURLConnection CON = (HttpURLConnection) obj.openConnection();
            //Sending the request
            CON.setRequestMethod("GET");
            int response = CON.getResponseCode();
            if(response==200) {
            	 return true;
            }else {
            	return false;
            }
           
        }
          
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

}
