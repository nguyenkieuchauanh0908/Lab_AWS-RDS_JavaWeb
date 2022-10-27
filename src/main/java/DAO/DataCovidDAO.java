package DAO;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import model.DataCovidViewModel;
import utils.DynamoDBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataCovidDAO {
    public static String Host = "localhost";
    public static String Port = "3306";
    public static String Type = "mysql";
    public static String Name = "root";
    public static String Password = "Nms10102002";
    public static String DBName = "Test";
    public static String DriveName = "com.mysql.jdbc.Driver";
    public static ArrayList<DataCovidViewModel> GetAllByDate(String date){
        ArrayList<DataCovidViewModel> res = new ArrayList<>();
        try{
            Class.forName(DriveName);
            Connection con = DriverManager.getConnection(
                    "jdbc:" + Type +"://" + Host + ":" + Port + "/" + DBName,Name,Password);

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from datacovid where date='"+date+"'");
            while(rs.next())
            {
                DataCovidViewModel dataCovidViewModel = new DataCovidViewModel();
                dataCovidViewModel.setId(Integer.toString(rs.getInt(1)));
                dataCovidViewModel.setAdmin2(rs.getString(2));
                dataCovidViewModel.setProvince_state(rs.getString(3));
                dataCovidViewModel.setCountry_region(rs.getString(4));
                dataCovidViewModel.setLast_update(rs.getString(5));
                dataCovidViewModel.setLat(rs.getString(6));
                dataCovidViewModel.setLong_(rs.getString(7));
                dataCovidViewModel.setConfirmed(rs.getString(8));
                dataCovidViewModel.setDeaths(rs.getString(9));
                dataCovidViewModel.setRecovered(rs.getString(10));
                dataCovidViewModel.setActive(rs.getString(11));
                dataCovidViewModel.setCombined_key(rs.getString(12));
                dataCovidViewModel.setIncident_rate(rs.getString(13));
                dataCovidViewModel.setCase_fatality_ratio(rs.getString(14));
                dataCovidViewModel.setDate(rs.getString(15));
                res.add(dataCovidViewModel);
            }
            con.close();
        }catch(Exception e){
            return null;
        }
        return res;
    }

    public static ArrayList<DataCovidViewModel> GetAllByDateDynamoDB(String date){
        ArrayList<DataCovidViewModel> res = new ArrayList<>();

        try{

            ScanRequest scanRequest = new ScanRequest()
                    .withTableName("datacovid")
                    .withAttributesToGet("id","admin2","province_state","country_region","last_update","lat","long_"
                            ,"confirmed","deaths","recovered","active","combined_key",
                            "incident_rate","case_fatality_ratio","date");

            ScanResult result = DynamoDBContext.getAmazonClient().scan(scanRequest);
            for (Map<String, AttributeValue> item : result.getItems()){
                AttributeValue dt = item.getOrDefault("date", new AttributeValue());
                if(!Objects.equals(dt.getS(), date))
                    continue;
                AttributeValue id = item.getOrDefault("id", new AttributeValue());
                AttributeValue admin2 = item.getOrDefault("admin2", new AttributeValue());
                AttributeValue province_state = item.getOrDefault("province_state", new AttributeValue());
                AttributeValue country_region = item.getOrDefault("country_region", new AttributeValue());
                AttributeValue last_update = item.getOrDefault("last_update", new AttributeValue());
                AttributeValue lat = item.getOrDefault("lat", new AttributeValue());
                AttributeValue long_ = item.getOrDefault("long_", new AttributeValue());
                AttributeValue confirmed = item.getOrDefault("confirmed", new AttributeValue());
                AttributeValue deaths = item.getOrDefault("deaths", new AttributeValue());
                AttributeValue recovered = item.getOrDefault("recovered", new AttributeValue());
                AttributeValue active = item.getOrDefault("active", new AttributeValue());
                AttributeValue combined_key = item.getOrDefault("combined_key", new AttributeValue());
                AttributeValue incident_rate = item.getOrDefault("incident_rate", new AttributeValue());
                AttributeValue case_fatality_ratio = item.getOrDefault("case_fatality_ratio", new AttributeValue());

                DataCovidViewModel dataCovidViewModel = new DataCovidViewModel();
                dataCovidViewModel.setId(id.getN());
                dataCovidViewModel.setAdmin2(admin2.getS());
                dataCovidViewModel.setProvince_state(province_state.getS());
                dataCovidViewModel.setCountry_region(country_region.getS());
                dataCovidViewModel.setLast_update(last_update.getS());
                dataCovidViewModel.setLat(lat.getS());
                dataCovidViewModel.setLong_(long_.getS());
                dataCovidViewModel.setConfirmed(confirmed.getS());
                dataCovidViewModel.setDeaths(deaths.getS());
                dataCovidViewModel.setRecovered(recovered.getS());
                dataCovidViewModel.setActive(active.getS());
                dataCovidViewModel.setCombined_key(combined_key.getS());
                dataCovidViewModel.setIncident_rate(incident_rate.getS());
                dataCovidViewModel.setCase_fatality_ratio(case_fatality_ratio.getS());
                dataCovidViewModel.setDate(dt.getS());
                res.add(dataCovidViewModel);

            }

        }catch(Exception e){
            return null;
        }

        return res;
    }
}
