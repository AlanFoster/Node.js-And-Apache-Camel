# Todo Use  trap 'kill $!' EXIT if the tests break horribly

test :
	make testBehaviour

# Run full behaviour tests written with cucumber
testBehaviour :
	make testNodeBehaviour

# Starts up the node app in test mode
# Then runs basic behaviour tests
testNodeBehaviour :
	@cd app; \
		NODE_ENV=testing node app.js & \
 	    	cd ../test/; \
		DEBUG_LEVEL=5 ./node_modules/.bin/cucumber.js ./features/ -t ~@ignore -f pretty; \
		kill $$!

.PHONY: install test testNodeBehaviour testConnectivity testAll 
