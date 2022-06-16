package br.inatel.cdg.database.interfaces;

import org.json.simple.JSONArray;

public interface ManipulateData {

    void putItem();

    void fileExistPutItem();

    void writeFile(JSONArray brownieArray);
}
