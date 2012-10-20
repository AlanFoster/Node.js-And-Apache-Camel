#     trap 'kill $!' EXIT

test :
	make testBehaviour

# Run mocha basic connectivity tests
testConnectivity :
	@./node_modules/.bin/mocha -R spec -u exports test/connectivity

# Run full behaviour tests written with cucumber
testBehaviour :
	@NODE_ENV=testing node app.js & \
 	    DEBUG_LEVEL=5 ./node_modules/.bin/cucumber.js test/features/ -t ~@ignore -f pretty; \
        kill $$!

.PHONY: install test testBehaviour testConnectivity