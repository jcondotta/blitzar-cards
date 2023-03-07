package com.blitzar.cards.argumentprovider;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class InvalidStringArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(StringUtils.EMPTY, StringUtils.SPACE, StringUtils.LF, "\t", null)
                .map(invalidStringValue -> Arguments.of(StringEscapeUtils.escapeJava(invalidStringValue), invalidStringValue));
        //TODO utilizar uma nova versao no escape utils
    }
}