package ru.job4j.controlTask;

import java.util.*;

/**
 * Created by Alexandr on 12.08.2017.
 */
public class SortSubdivision {
    public String[] sortAscendingCodes(String[] codesSubdivision) {
        List<String> listCodes = new LinkedList<>();
        Collections.addAll(listCodes, codesSubdivision);
        List<String> listAbsentCodes = listAbsentCodes(listCodes);
        if(!listAbsentCodes.isEmpty()) {
            for(String x : listAbsentCodes) {
                listCodes.add(x);
            }
        }
        Collections.sort(listCodes);
        return listCodes.toArray(new String[listCodes.size()]);
    }

    public String[] sortDecreaseCodes(String[] codesSubdivisions) {
        List<String> listCodes = new LinkedList<>();
        Collections.addAll(listCodes, codesSubdivisions);
        List<String> listAbsentCodes = listAbsentCodes(listCodes);
        if(!listAbsentCodes.isEmpty()) {
            for(String x : listAbsentCodes) {
                listCodes.add(x);
            }
        }
        Collections.sort(listCodes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                boolean o1SubstringO2 = o2.startsWith(o1) || o1.startsWith(o2);
                return o1SubstringO2 ? o1.compareTo(o2) : o2.compareTo(o1);
            }
        });
        return listCodes.toArray(new String[listCodes.size()]);
    }

    private List<String> listAbsentCodes(List<String> listCodes) {
        List<String> listAbsentCodes = new LinkedList<>();
        for (String x : listCodes) {
            if (x.contains("\\")) {
                int indexBackslash = x.lastIndexOf("\\");
                String overCode = x.substring(0, indexBackslash);
                if (!listCodes.contains(overCode) && !listAbsentCodes.contains(overCode)) {
                    listAbsentCodes.add(overCode);
                }
            }
        }
        return listAbsentCodes;
    }
}
