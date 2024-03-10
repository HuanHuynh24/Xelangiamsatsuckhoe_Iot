package com.example.gimstsckho_iot.model;

import static android.provider.Telephony.Carriers.PASSWORD;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConSQL {
    Connection connection;
    @SuppressLint("NewApi")
    public Connection conclass() {
        String ip = "192.168.1.9", port = "1433", db = "hnh2319", username = "sa", password = "huanhuynh2402";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectUrl = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectUrl);
            Log.d("Connection", "Kết nối tới cơ sở dữ liệu thành công");
        } catch (SQLException e) {
            Log.e("SQL Exception", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("Class Not Found", e.getMessage());
        }
        return connection;
    }
}
