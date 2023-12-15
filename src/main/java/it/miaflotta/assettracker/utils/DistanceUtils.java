package it.miaflotta.assettracker.utils;

import it.miaflotta.assettracker.models.dto.position.route.DrivingDistanceDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DistanceUtils {
    public BigDecimal calculateDistance() {
        return null;
    }

    public double calculateApproximateDistanceInMeters(double lat1, double long1, double lat2, double long2) {
        return org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
    }

    public double calculateApproximateDistanceInKm(double lat1, double long1, double lat2, double long2) {
        return calculateApproximateDistanceInMeters(lat1, long1, lat2, long2) * 0.001;
    }

    public double convertFromMetersToKm(double mt) {
        return (mt * 0.001);
    }
}
