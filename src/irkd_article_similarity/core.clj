(ns irkd-article-similarity.core)

(require '[clojure.string :as str])
(require '[clojure.set :as set])
(require '[clojure.math.numeric-tower :as math])
(use 'clojure.pprint)
(use 'clj-fuzzy.stemmers)

;; http://stackoverflow.com/questions/10751638/clojure-rounding-to-decimal-places
(defn round2
  "Round a double to the given precision (number of significant digits)"
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))


;; http://www.lextek.com/manuals/onix/stopwords1.html
(def stop-words #{"a" "about" "above" "across" "after" "again" "against" "all" "almost" "alone" "along" "already" "also" "although" "always"
  "among" "an" "and" "another" "any" "anybody" "anyone" "anything" "anywhere" "are" "area" "areas" "around" "as" "ask" "asked"
  "asking" "asks" "at" "away" "b" "back" "backed" "backing" "backs" "be" "became" "because" "become" "becomes" "been" "before"
  "began" "behind" "being" "beings" "best" "better" "between" "big" "both" "but" "by" "c" "came" "can" "cannot" "case" "cases"
  "certain" "certainly" "clear" "clearly" "come" "could" "d" "did" "differ" "different" "differently" "do" "does" "done"
  "down" "downed" "downing" "downs" "during" "e" "each" "early" "either" "end" "ended" "ending" "ends" "enough" "even" "evenly"
  "ever" "every" "everybody" "everyone" "everything" "everywhere" "f" "face" "faces" "fact" "facts" "far" "felt" "few" "find" "finds"
  "first" "for" "four" "from" "full" "fully" "further" "furthered" "furthering" "furthers" "g" "gave" "general" "generally" "get" "gets"
  "give" "given" "gives" "go" "going" "good" "goods" "got" "great" "greater" "greatest" "group" "grouped" "grouping" "groups" "h" "had" "has"
  "have" "having" "he" "her" "here" "herself" "higher" "highest" "him" "himself" "his" "how" "however" "i" "if" "important" "in"
  "interest" "interested" "interesting" "interests" "into" "is" "it" "its" "itself" "j" "just" "k" "keep" "keeps" "kind" "knew" "know" "known" "knows" "l"
  "large" "largely" "last" "later" "latest" "least" "less" "let" "lets" "like" "likely" "long" "longer" "longest" "m" "made" "make" "making" "man" "many"
  "may" "me" "member" "members" "men" "might" "more" "most" "mostly" "mr" "mrs" "much" "must" "my" "myself" "n" "necessary" "need" "needed" "needing" "needs"
  "never" "new" "newer" "newest" "next" "no" "nobody" "non" "noone" "not" "nothing" "now" "nowhere" "number" "numbers" "o" "of" "off" "often" "old" "older"
  "oldest" "on" "once" "one" "only" "open" "opened" "opening" "opens" "or" "order" "ordered" "ordering" "orders" "other" "others" "our" "out" "over" "p" "part" "parted"
  "parting" "parts" "per" "perhaps" "place" "places" "point" "pointed" "pointing" "points" "possible" "present" "presented" "presenting" "presents" "problem" "problems" "put" "puts"
  "q" "quite" "r" "rather" "really" "right" "room" "rooms" "s" "said" "same" "saw" "say" "says" "second" "seconds" "see" "seem" "seemed" "seeming" "seems" "sees" "several" "shall"
  "she" "should" "show" "showed" "showing" "shows" "side" "sides" "since" "small" "smaller" "smallest" "so" "some" "somebody" "someone" "something" "somewhere"
  "state" "states" "still" "such" "sure" "t" "take" "taken" "than" "that" "the" "their" "them" "then" "there" "therefore" "these" "they" "thing" "things" "think" "thinks"
  "this" "those" "though" "thought" "thoughts" "three" "through" "thus" "to" "today" "together" "too" "took" "toward" "turn" "turned" "turning" "turns" "two" "u" "under" "until" "up"
  "upon" "us" "use" "used" "uses" "v" "very" "w" "want" "wanted" "wanting" "wants" "was" "way" "ways" "we" "well" "wells" "went" "were" "what" "when" "where" "whether" "which" "while"
  "who" "whole" "whose" "why" "will" "with" "within" "without" "work" "worked" "working" "works" "would" "x" "y" "year" "years" "yet" "you" "young" "younger" "youngest" "your" "yours" "z"})

(defn clean-word [word]
  (str/replace (str/lower-case (str/trim word)) #"[^a-z]" ""))

(defn stop-word? [word]
  (contains? stop-words word))

(defn get-keywords [file]
  (def all-words (str/split (slurp file) #"\s"))
  (def candidates (remove str/blank? (map clean-word all-words)))
  (remove stop-word? candidates)
  ;; (map porter (remove stop-word? candidates))
  )

(defn word-frequency [words]
  (reverse (sort-by last (frequencies words))))

(defn get-top-10-words [words]
  (take 10 (word-frequency words)))

(defn get-term-document-row [terms document]
  (def unique-terms (set document))
  (set (filter (partial contains? unique-terms) terms)))

(defn get-term-document-matrix [terms documents]
  (map (partial get-term-document-row terms) documents))

(defn dices-coefficient [d q]
  (def d-intersect-q (count (set/intersection d q)))
  (def d-size (count d))
  (def q-size (count q))
  (def coefficient (/ (* 2 d-intersect-q) (+ d-size q-size)))

  (round2 2 (double coefficient)))

(defn jaccards-coefficient [d q]
  (def d-intersect-q (count (set/intersection d q)))
  (def d-union-q (count (set/union d q)))
  (def coefficient (/ d-intersect-q d-union-q))

  (round2 2 (double coefficient)))

(defn cosine-coefficient [d q]
  (def d-intersect-q (count (set/intersection d q)))
  (def d-root (math/sqrt (count d)))
  (def q-root (math/sqrt (count q)))

  (def coefficient (/ d-intersect-q (* d-root q-root)))

  (round2 2 (double coefficient)))

(defn overlap-coefficient [d q]
  (def d-intersect-q (count (set/intersection d q)))
  (def d-size (count d))
  (def q-size (count q))

  (def coefficient (/ d-intersect-q (min d-size q-size)))

  (round2 2 (double coefficient)))

(defn calc-for-article-all-documents [match-fn documents d]
  (map (partial match-fn d) documents))


(defn -main []
  (def files '("resources/article1.txt"
               "resources/article2.txt"
               "resources/article3.txt"
                "resources/article4.txt"
                "resources/article5.txt"
                "resources/article6.txt"
                "resources/article7.txt"
                "resources/article8.txt"
                "resources/article9.txt"
                "resources/article10.txt"))

  (def articles (map get-keywords files))
  (def words (flatten articles))

  (def popular-word-freqs (get-top-10-words words))
  (def popular-words (map first popular-word-freqs))
  (println "*********************************")
  (println "10 most popular words: ")
  (pprint popular-words)
  (println "*********************************")


  (def term-document-matrix (get-term-document-matrix popular-words articles))
  (println "********************************")
  (println "Term document matrix")
  (pprint term-document-matrix)
  (println "********************************")

  ;; (pprint (dices-coefficient (first term-document-matrix) (second term-document-matrix)))
  ;; (pprint (calc-for-article-all-documents dices-coefficient term-document-matrix (first term-document-matrix)))
  (println "********************************")
  (println "dices coefficent between all articles")
  (pprint (map (partial calc-for-article-all-documents dices-coefficient term-document-matrix) term-document-matrix))
  (println "********************************")

  (println "********************************")
  (println "jaccards coefficent between all articles")
  (pprint (map (partial calc-for-article-all-documents jaccards-coefficient term-document-matrix) term-document-matrix))
  (println "********************************")

  (println "********************************")
  (println "cosine coefficent between all articles")
  (pprint (map (partial calc-for-article-all-documents cosine-coefficient term-document-matrix) term-document-matrix))
  (println "********************************")

  (println "********************************")
  (println "overlap coefficent between all articles")
  (pprint (map (partial calc-for-article-all-documents overlap-coefficient term-document-matrix) term-document-matrix))
  (println "********************************")

  )
