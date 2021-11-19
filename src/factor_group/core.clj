(ns factor-group.core)

(defmacro factor-group
  [data group-data bindings & body]
  (let [group-symbols# (vec (take-nth 2 bindings))
        group-keys#    (take-nth 2 (rest bindings))
        groups#        `(group-by (apply juxt [~@group-keys#]) ~data)]
    `(mapv (fn [group#]
             (let [[~group-symbols# ~group-data] group#]
               ~@body)) ~groups#)))
