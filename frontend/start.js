var exec = require('child_process').exec;
var os = require('os');

function puts(error, stdout, stderr) {
    console.log(stdout); // Using console.log instead of sys.puts
}

// Run command depending on the OS
if (os.type() === 'Darwin') {
    exec('export NODE_OPTIONS=--openssl-legacy-provider && react-scripts start', puts);
} else if (os.type() === 'Windows_NT') {
    exec('react-scripts --openssl-legacy-provider start', puts);
} else {
    throw new Error('Unsupported OS found: ' + os.type());
}