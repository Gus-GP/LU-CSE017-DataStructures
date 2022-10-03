# Huffman Encoding

## Project Overview

A file compression algorithm that uses binary trees and priority queues is implemented. The program will allow the user to compress and decompress files using the standard Huffman algorithm for encoding and decoding.

## Background

Huffman encoding is an algorithm devised by David A. Huffman of MIT in 1952 for compressing textual data to make a file occupy a smaller number of bytes. Though it is a relatively simple compression algorithm, Huffman is powerful enough that variations of it are still used today in computer networks, fax machines, modems, HDTV, and other areas. Normally textual data is stored in a standard format of 8 bits per character, using an encoding called ASCII that maps each character to a binary integer value from 0-255. The idea of Huffman encoding is to abandon the rigid 8-bits-per-character requirement, and instead to use binary encodings of different lengths for different characters. The advantage of doing this is that if a character occurs frequently in the file, such as the very common letter 'e', it could be given a shorter encoding (i.e., fewer bits), making the overall file smaller. The tradeoff is that some characters may need to use encodings that are longer than 8 bits, but this is reserved for characters that occur infrequently, so the extra cost is worth it, on the balance

**Problem Description Source [here](http://web.stanford.edu/class/archive/cs/cs106b/cs106b.1178/assn/huffman.pdf)**









