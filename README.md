# Testing with Shadow CLJS and Calva

## Using node-repl

- `yarn shadow-cljs node-repl`
- `(require 'app.core-spec)`
- `(cljs.test/run-tests 'app.core-spec)`

Pros:
- needs only node-repl
- can selectively run tests

Cons:
- namespace needs to be reloaded manually
- node-repl isn't as nice as the Calva repl window (command repeat, autocomplete, etc)
- no editor intellisense
- can't use Calva repl because there's no application connected to the repl


## Using a node-test build with autorun

- `yarn shadow-cljs watch test`

Pros:
- needs only shadow-cljs
- all tests run on save

Cons:
- can't selectively run tests
- no editor intellisense
- can't use Calva repl because there's no application connected to the repl


## Using jack in on a node-test build with autorun

- ctrl+shift+p jack in
- select `test` build
- select `test` as build to connect to

Pros:
- editor intellisense
- all tests run on save

Cons:
- can't selectively run tests
- can't use Calva repl because there's no application connected to the repl
- ctrl+shift+p `Calva: run all tests` doesn't work (probably https://github.com/BetterThanTomorrow/calva/issues/311)
```
Evaluating file: core_spec.cljs
No application has connected to the REPL server. Make sure your JS environment has loaded your compiled ClojureScript code.

No results from file evaluation.
Running namespace tests…

No tests found. 😱, ns: 0, vars: 0
```

## Using jack in on a node-test build with autorun, but connecting to node-repl

- ctrl+shift+p jack in
- select `test` build
- select `node-repl` as build to connect to

Pros:
- editor intellisense
- all tests run on save
- can use Calva repl
- can selectively run tests on Calva repl

Cons:
- ctrl+shift+p `Calva: run all tests` doesn't work (same as above)
- must manually refresh namespace with ctrl+shift+p "Calva: load current namespace in repl" (or turn on "run load-file on save" option)
- running tests by name in Calva repl shows nil return, must check separate calva output pane (probably https://github.com/thheller/shadow-cljs/issues/373)
```
(Calva repl)
app.core-spec=> (my-test)
nil
```
```
(Calva output pane)
<cljs-repl#5>
FAIL in (my-test) (at d:475:6)
This test should fail
<cljs-repl#5>expected: (= 1 4)
  actual: (not (= 1 4))
```

## Using jack in on a node-test build without autorun, but connecting to node-repl

Same as above, but removing the autorun is better when tests are slow.

Run individual tests in calva repl by name `(my-test)`.
Run all tests via `(cljs.test/run-tests 'app.core-spec)`.

Cons:
- trying to load the namespace on calva repl shows error on output pane (https://github.com/thheller/shadow-cljs/issues/587)
```
Evaluating file: core_spec.cljs
symbol app.core-spec already provided by [:shadow.cljs.repl/resource "app/core_spec.cljs"], conflict with [:shadow.build.classpath/resource "app\\core_spec.cljs"]
{:provide app.core-spec, :conflict [:shadow.cljs.repl/resource "app/core_spec.cljs"], :resource-id [:shadow.build.classpath/resource "app\\core_spec.cljs"]}
ExceptionInfo: symbol app.core-spec already provided by [:shadow.cljs.repl/resource "app/core_spec.cljs"], conflict with [:shadow.build.classpath/resource "app\\core_spec.cljs"]
	shadow.build.data/add-provide (data.clj:91)
	shadow.build.data/add-provide (data.clj:86)
```