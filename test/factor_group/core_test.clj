(ns factor-group.core-test
  (:require [clojure.test :refer [deftest is]]
            [factor-group.core :refer [factor-group]]
            [clojure.string :as str]))

(def all-patients
  '({:firstname "Adam"
     :lastname  "Smith"
     :diagnosis "COVID-19"
     :treated   true}
    {:firstname "Joseph"
     :lastname  "Goodman"
     :diagnosis "COVID-19"
     :treated   true}
    {:firstname "Werner"
     :lastname  "Ziegler"
     :diagnosis "COVID-19"
     :treated   false}
    {:firstname "Boris"
     :lastname  "Henry"
     :diagnosis "Healthy"
     :treated   false}
    {:firstname "Johnny"
     :lastname  "Grayhand"
     :diagnosis "COVID-76"
     :treated   false}))

(def output " начало обработки группы пациентов с диагнозом  COVID-19 , подвергавшихся лечению
 количество пациентов в группе -  2
 фамилии пациентов -  Smith, Goodman
 начало обработки группы пациентов с диагнозом  COVID-19 , НЕ подвергавшихся лечению
 количество пациентов в группе -  1
 фамилии пациентов -  Ziegler
 начало обработки группы пациентов с диагнозом  Healthy , НЕ подвергавшихся лечению
 количество пациентов в группе -  1
 фамилии пациентов -  Henry
 начало обработки группы пациентов с диагнозом  COVID-76 , НЕ подвергавшихся лечению
 количество пациентов в группе -  1
 фамилии пациентов -  Grayhand
")

(deftest test-factor-group
  (let [output-res (with-out-str
                     (let [res (factor-group all-patients
                                             patients-group
                                             [treated?     :treated
                                              disease-name :diagnosis]
                                             (println " начало обработки группы пациентов с диагнозом " disease-name
                                                      (if treated? ", подвергавшихся лечению"
                                                          ", НЕ подвергавшихся лечению"))
                                             (println " количество пациентов в группе - " (count patients-group))
                                             (println " фамилии пациентов - " (str/join ", " (map :lastname patients-group)))
                                             (count patients-group))]
                       (is (= '(2 1 1 1) res))))]
    (is (= output output-res))))
