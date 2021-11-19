format:
	lein cljfmt fix

checks: check-format check-kondo

check-format:
	lein cljfmt check

check-kondo:
	clj-kondo --lint src test

test:
	lein test

.PHONY: test
