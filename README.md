# PigLatinApp
Simple app that converts English into Pig Latin. Mostly just to practice programming

Resource:
https://www.cs.duke.edu/courses/spring03/cps006/dr/assn/assign2/

Rules to fulfill:
Basically, the Pig Latin system used here works as follows:
 - Words that start with a vowel (A, E, I, O, U) simply have "WAY" appended to the end of the word.
 - Words that start with a consonant have all consonant letters up to the first vowel moved to the end of the word (as opposed to just the    first consonant letter), and "AY" is appended.      ('Y' is counted as a vowel in this context)
 
 The algorithm incorporates the following features and special case functionality:  
 - Ensures proper capitalization  
 - Correct upper case and lower case formatting  
 - Correctly translates "qu" (e.g., ietquay instead of uietqay)  
 - Differentiates between "Y" as vowel and "Y" as consonant (e.g. yellow = elloyay and style = ylestay) — (except for a very few              exceptions)  
 - Correctly translates contractions  
 - Hyphenated words are treated as two words  
 - Words may consist of alphabetic characters only (A-Z and a-z)  
 - All punctuation, numerals, symbols and whitespace are not modified
 - If a word does not contain a vowel, do nothing
