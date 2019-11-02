(ns app.core-spec
  (:require [cljs.test :refer-macros [deftest is]]))

(deftest my-test
  (is (= 1 2) "This test should fail"))

(defn main [])