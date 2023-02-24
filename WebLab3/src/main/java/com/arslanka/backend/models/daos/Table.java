package com.arslanka.backend.models.daos;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Named("tableBean")
@SessionScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Serializable {
    private List<TableRow> tableRows;
}
