# Huffman-encoder-decoder
Implemented three data structures :Binary  Heap, 4-way  cache  optimized  heap and Pairing Heap. 
Constructed Huffman trees using these three data structures and then measure run time using a data set of 1 million entries.

After determining the best performance data strcuture, implemented the encoder and decoder for the same.

The complexity of the implemented decoding algorithm is: O(b*n)
Where, b is the bit length
n is the number of data values 

To run:
write the following commands on the terminal:
$ java encoder <input_file_name>
$ java decoder <encoded_file_name> <code_table_file_name>
