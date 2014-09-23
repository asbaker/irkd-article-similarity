# irkd-article-similarity

Collect 10 news stories, about 200 words or so, from the web. Make sure stories represent 2-3 different topics, for example politics, international relations, sports etc. Apply text processing on this collection to extract the set of keywords/indexing terms.Select the top ten most frequent keywords. Assuming Boolean model and using the Dice’s coefficient, compute the match score between any ten pairs of stories. Repeat the match score computations for the other three measures discussed in the class. Comment on the relationships between the match scores given by different coefficients. Next, compute the term weights for the selected keywords for the most similar story pair given by the Dice’s coefficient and determine the similarity measure for the same story pair following the vector space model.

## Usage

lein run

## License

The MIT License (MIT)

Copyright (c) 2014 Anthony S Baker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
