#     trap 'kill $!' EXIT

test :
	@NODE_ENV=testing node app.js & \
 	    DEBUG_LEVEL=5 ./node_modules/.bin/cucumber.js test/features/ -f pretty; \
        kill $$!

.PHONY: install test