# irkd-article-similarity

Collect 10 news stories, about 200 words or so, from the web. Make sure stories represent 2-3 different topics, for example politics, international relations, sports etc. Apply text processing on this collection to extract the set of keywords/indexing terms.Select the top ten most frequent keywords. Assuming Boolean model and using the Dice’s coefficient, compute the match score between any ten pairs of stories. Repeat the match score computations for the other three measures discussed in the class. Comment on the relationships between the match scores given by different coefficients. Next, compute the term weights for the selected keywords for the most similar story pair given by the Dice’s coefficient and determine the similarity measure for the same story pair following the vector space model.

## Usage

lein run


## Output
  *********************************
  10 most popular words:
  (["percent" 25]
  ["home" 16]
  ["cash" 14]
  ["investors" 12]
  ["time" 12]
  ["democratic" 11]
  ["senate" 11]
  ["obama" 11]
  ["yahoo" 10]
  ["market" 9])
  *********************************
  ********************************
  Term document matrix
  (#{"obama" "percent" "time" "senate" "democratic"}
  #{"obama" "percent" "time" "senate" "home" "democratic"}
  #{"obama" "percent" "time" "senate" "democratic"}
  #{"time"}
  #{"home"}
  #{"percent" "home"}
  #{"percent" "time" "market"}
  #{"percent" "cash" "market"}
  #{"percent" "cash" "investors" "market" "home"}
  #{"yahoo" "percent" "cash" "investors" "market"})
  ********************************
  ********************************
  dices coefficent between all articles
  ((1.0 0.91 1.0 0.33 0.0 0.29 0.5 0.25 0.2 0.2)
  (0.91 1.0 0.91 0.29 0.29 0.5 0.44 0.22 0.36 0.18)
  (1.0 0.91 1.0 0.33 0.0 0.29 0.5 0.25 0.2 0.2)
  (0.33 0.29 0.33 1.0 0.0 0.0 0.5 0.0 0.0 0.0)
  (0.0 0.29 0.0 0.0 1.0 0.67 0.0 0.0 0.33 0.0)
  (0.29 0.5 0.29 0.0 0.67 1.0 0.4 0.4 0.57 0.29)
  (0.5 0.44 0.5 0.5 0.0 0.4 1.0 0.67 0.5 0.5)
  (0.25 0.22 0.25 0.0 0.0 0.4 0.67 1.0 0.75 0.75)
  (0.2 0.36 0.2 0.0 0.33 0.57 0.5 0.75 1.0 0.8)
  (0.2 0.18 0.2 0.0 0.0 0.29 0.5 0.75 0.8 1.0))
  ********************************
  ********************************
  jaccards coefficent between all articles
  ((1.0 0.83 1.0 0.2 0.0 0.17 0.33 0.14 0.11 0.11)
  (0.83 1.0 0.83 0.17 0.17 0.33 0.29 0.13 0.22 0.1)
  (1.0 0.83 1.0 0.2 0.0 0.17 0.33 0.14 0.11 0.11)
  (0.2 0.17 0.2 1.0 0.0 0.0 0.33 0.0 0.0 0.0)
  (0.0 0.17 0.0 0.0 1.0 0.5 0.0 0.0 0.2 0.0)
  (0.17 0.33 0.17 0.0 0.5 1.0 0.25 0.25 0.4 0.17)
  (0.33 0.29 0.33 0.33 0.0 0.25 1.0 0.5 0.33 0.33)
  (0.14 0.13 0.14 0.0 0.0 0.25 0.5 1.0 0.6 0.6)
  (0.11 0.22 0.11 0.0 0.2 0.4 0.33 0.6 1.0 0.67)
  (0.11 0.1 0.11 0.0 0.0 0.17 0.33 0.6 0.67 1.0))
  ********************************
  ********************************
  cosine coefficent between all articles
  ((1.0 0.91 1.0 0.45 0.0 0.32 0.52 0.26 0.2 0.2)
  (0.91 1.0 0.91 0.41 0.41 0.58 0.47 0.24 0.37 0.18)
  (1.0 0.91 1.0 0.45 0.0 0.32 0.52 0.26 0.2 0.2)
  (0.45 0.41 0.45 1.0 0.0 0.0 0.58 0.0 0.0 0.0)
  (0.0 0.41 0.0 0.0 1.0 0.71 0.0 0.0 0.45 0.0)
  (0.32 0.58 0.32 0.0 0.71 1.0 0.41 0.41 0.63 0.32)
  (0.52 0.47 0.52 0.58 0.0 0.41 1.0 0.67 0.52 0.52)
  (0.26 0.24 0.26 0.0 0.0 0.41 0.67 1.0 0.77 0.77)
  (0.2 0.37 0.2 0.0 0.45 0.63 0.52 0.77 1.0 0.8)
  (0.2 0.18 0.2 0.0 0.0 0.32 0.52 0.77 0.8 1.0))
  ********************************
  ********************************
  overlap coefficent between all articles
  ((1.0 1.0 1.0 1.0 0.0 0.5 0.67 0.33 0.2 0.2)
  (1.0 1.0 1.0 1.0 1.0 1.0 0.67 0.33 0.4 0.2)
  (1.0 1.0 1.0 1.0 0.0 0.5 0.67 0.33 0.2 0.2)
  (1.0 1.0 1.0 1.0 0.0 0.0 1.0 0.0 0.0 0.0)
  (0.0 1.0 0.0 0.0 1.0 1.0 0.0 0.0 1.0 0.0)
  (0.5 1.0 0.5 0.0 1.0 1.0 0.5 0.5 1.0 0.5)
  (0.67 0.67 0.67 1.0 0.0 0.5 1.0 0.67 0.67 0.67)
  (0.33 0.33 0.33 0.0 0.0 0.5 0.67 1.0 1.0 1.0)
  (0.2 0.4 0.2 0.0 1.0 1.0 0.67 1.0 1.0 0.8)
  (0.2 0.2 0.2 0.0 0.0 0.5 0.67 1.0 0.8 1.0))
  ********************************
  ********************************
  Similar document term weights
  (["obama" 1] ["percent" 1] ["time" 1] ["senate" 1] ["democratic" 6])
  (["obama" 2] ["percent" 3] ["time" 1] ["senate" 7] ["democratic" 2])
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 2 (both political)
  0.4829171308039098
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 1 (highly related)
  0.5452798337435363
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 0 (same article)
  0.9999999999999998
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 3 (only related by 1 term)
  0.15811388300841897
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 4 (not related at all)
  0.0
  ********************************
  ********************************
  cosine similarity measure between doc 0 and doc 9 (a different class)
  0.030151134457776358
  ********************************

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
