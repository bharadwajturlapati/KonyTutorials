var sdkobj = new kony.sdk();
sdkObject.init("<clientID>","<clientSecret>","<appconfigurl>",function(res){console.warn("completed")},function(res){console.err("Incomplete");})