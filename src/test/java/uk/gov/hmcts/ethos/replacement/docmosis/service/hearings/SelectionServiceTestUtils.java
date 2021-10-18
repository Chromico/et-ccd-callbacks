package uk.gov.hmcts.ethos.replacement.docmosis.service.hearings;

import uk.gov.hmcts.ecm.common.model.bulk.types.DynamicFixedListType;
import uk.gov.hmcts.ecm.common.model.bulk.types.DynamicValueType;
import uk.gov.hmcts.ecm.common.model.ccd.CaseData;
import uk.gov.hmcts.ecm.common.model.multiples.MultipleData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SelectionServiceTestUtils {

    final static int DEFAULT_LIST_SIZE = 3;

    public static CaseData createCaseData(TribunalOffice tribunalOffice) {
        var caseData = new CaseData();
        caseData.setOwningOffice(tribunalOffice.getOfficeName());
        return caseData;
    }

    public static MultipleData createMultipleData(String tribunalOffice) {
        var multipleData = new MultipleData();
        multipleData.setOwningOffice(tribunalOffice);
        return multipleData;
    }

    public static List<DynamicValueType> createListItems(String codeBase, String labelBase) {
        var listItems = new ArrayList<DynamicValueType>();
        for (var i = 1; i <= DEFAULT_LIST_SIZE; i++) {
            listItems.add(DynamicValueType.create(codeBase + i, labelBase + i));
        }

        return listItems;
    }

    public static void verifyDynamicFixedListNoneSelected(DynamicFixedListType dynamicFixedListType,
                                                   String codeBase, String labelBase) {
        assertEquals(DEFAULT_LIST_SIZE, dynamicFixedListType.getListItems().size());
        for (var i = 1; i <= DEFAULT_LIST_SIZE; i++) {
            var listItem = dynamicFixedListType.getListItems().get(i-1);
            assertEquals(codeBase + i, listItem.getCode());
            assertEquals(labelBase + i, listItem.getLabel());
        }
        assertNull(dynamicFixedListType.getValue());
        assertNull(dynamicFixedListType.getSelectedCode());
        assertNull(dynamicFixedListType.getSelectedLabel());
    }

    public static void verifyDynamicFixedListSelected(DynamicFixedListType dynamicFixedListType,
                                                   String codeBase, String labelBase, DynamicValueType selectedValue) {
        assertEquals(DEFAULT_LIST_SIZE, dynamicFixedListType.getListItems().size());
        verifyListItems(dynamicFixedListType.getListItems(), codeBase, labelBase);
        assertEquals(selectedValue.getCode(), dynamicFixedListType.getValue().getCode());
        assertEquals(selectedValue.getLabel(), dynamicFixedListType.getValue().getLabel());
        assertEquals(selectedValue.getCode(), dynamicFixedListType.getSelectedCode());
        assertEquals(selectedValue.getLabel(), dynamicFixedListType.getSelectedLabel());
    }

    public static void verifyListItems(List<DynamicValueType> listItems, String codeBase, String labelBase) {
        for (var i = 1; i <= DEFAULT_LIST_SIZE; i++) {
            var listItem = listItems.get(i-1);
            assertEquals(codeBase + i, listItem.getCode());
            assertEquals(labelBase + i, listItem.getLabel());
        }
    }
}
