(ns factor-group.core)

(defmacro factor-group
  "The macro get `data` which is a list of maps,
  and groups it by keys obtained from a vector `bindings`.

  `group-data` is a symbol of data group

  `bindings`` is a vector like [value1 :key1 value2 :key2],
  where key[n] is the grouping key and value[n] is the symbol
  associated with the current value in the group with this key

  `body` is a macro body
  "
  [data group-data bindings & body]
  (let [group-symbols# (vec (take-nth 2 bindings))
        group-keys#    (take-nth 2 (rest bindings))
        groups#        `(group-by (apply juxt [~@group-keys#]) ~data)
        res#           `(mapv (fn [group#]
                                (let [[~group-symbols# ~group-data] group#]
                                  ~@body)) ~groups#)]
    (reverse (into '() res#))))
