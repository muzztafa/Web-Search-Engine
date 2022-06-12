# Web-Search-Engine

## Details:
It's a console based web search engine created on Java for my Advanced Computing Concepts' term project.

It's features:

1) It Crawls any given link(s) as input recursively and store them as text files (uses regex to validate given URLs).
2) It ranks pages based on the frequency of word in a file (the word being searched) using Boyer Moore pattern matching algorithm for searching.
3) If the word being searched doesn't exist in the crawled files, it predicts next closest words present in the files using the Edit Distance algorithm.
