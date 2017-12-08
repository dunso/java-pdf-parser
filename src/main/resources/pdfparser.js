'use strict';

var pdfParser = require('./node_modules/pdf-parser');

function getJson(PDF_PATH, callback) {
    pdfParser.pdf2json(PDF_PATH, function (error, pdf) {
        if (error != null) {
            callback(error);
        } else {
            callback(JSON.stringify(pdf));
        }
    });
}

exports.getJson = getJson;