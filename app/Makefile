#     trap 'kill $!' EXIT

test :
	make testConnectivity

# Run mocha basic connectivity tests
# This ensuresensures there is a valid webservice to connect to
testConnectivity :
	@./node_modules/.bin/mocha -R spec -u exports test/mocha

.PHONY: install test testBehaviour testConnectivity testAll