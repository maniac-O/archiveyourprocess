package com.SH.Invest_Info_Compilation.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum NoticeType {
    INVEST_STOCK ("주식"),
    INVEST_COIN ("코인"),
    INVEST_FUTURES ("선물"),
    ASSET_RISK ("리스크"),
    ASSET_RATIO ("비중 관리"),
    STUDY_THEORY ("공부 이론"),
    STUDY_ACTION ("공부 실전");

    private final String label;

    NoticeType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static List<String> getNames(){
        return Stream.of(NoticeType.values())
                .map(m -> m.name())
                .collect(Collectors.toList());
    }
    public static List<String> getLabels(){
        return Stream.of(NoticeType.values())
                .map(m -> m.label())
                .collect(Collectors.toList());
    }
}
