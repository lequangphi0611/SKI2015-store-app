const moduleSources = [
    './app/app.module.js'
];

(() => {

    'use trict';
    
    moduleSources.forEach(writeScriptTag);

}) ();

function writeScriptTag(src)  {
    var scriptTag = document.createElement('script');
    scriptTag.setAttribute('src', src);
    document.body.appendChild(scriptTag);
}