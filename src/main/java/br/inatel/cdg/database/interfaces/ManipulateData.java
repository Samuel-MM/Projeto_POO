package br.inatel.cdg.database.interfaces;

import org.json.simple.JSONObject;

public interface ManipulateData {

    void selectItem(String brownieName);

    boolean findItem(JSONObject brownie, String brownieName);
}
