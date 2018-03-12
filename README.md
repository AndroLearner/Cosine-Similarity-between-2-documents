# Cosine-Similarity-between-2-documents
How similar two documents are?

The similarities between documents are to be determined by the degree of the overlapping in
contents of two documents. There are many different and complex algorithms to answer this
question. Cosine Similarity is a measure of similarity between two vectors by measuring the cosine of the angle between them
and the algorithm has been used in search, text mining, and data mining. The cosine of the angle
between two vectors basically determines whether two vectors are pointing in roughly the same
direction or not. (http://en.wikipedia.org/wiki/Cosine_similarity)

In this program:
* A word is a sequence of letters [a..zA..Z] that does not include digits [0..9] and the underscore
character. Here are examples of non-words: abc123, a12bc, 1234, ab_c, abc_
* The distance between two identical documents is 0.
* And if two texts or documents do not have any common words, then the distance is Pi/2.
