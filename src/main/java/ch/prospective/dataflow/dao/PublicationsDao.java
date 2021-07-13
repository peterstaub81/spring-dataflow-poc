package ch.prospective.dataflow.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ch.prospective.dataflow.model.DashboardSearchRecord;

@Repository
public class PublicationsDao {

    public List<DashboardSearchRecord> getPublicationsFromJb() {
        return new ArrayList<>();
    }
}
