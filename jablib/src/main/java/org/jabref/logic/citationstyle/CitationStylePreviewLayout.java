package org.jabref.logic.citationstyle;

import java.util.List;

import org.jabref.logic.preview.PreviewLayout;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.BibEntryTypesManager;

public final class CitationStylePreviewLayout implements PreviewLayout {
    private final CitationStyle citationStyle;
    private final BibEntryTypesManager bibEntryTypesManager;

    public CitationStylePreviewLayout(CitationStyle citationStyle, BibEntryTypesManager bibEntryTypesManager) {
        this.citationStyle = citationStyle;
        this.bibEntryTypesManager = bibEntryTypesManager;
    }

    @Override
    public String generatePreview(BibEntry entry, BibDatabaseContext databaseContext) {
        if (!citationStyle.hasBibliography()) {
            // style has no bibliography formatting instructions - fall back to citation
            return CitationStyleGenerator.generateCitation(List.of(entry), citationStyle.getSource(), CitationStyleOutputFormat.HTML, databaseContext, bibEntryTypesManager);
        }
        return CitationStyleGenerator.generateBibliography(List.of(entry), citationStyle.getSource(), CitationStyleOutputFormat.HTML, databaseContext, bibEntryTypesManager).getFirst();
    }

    @Override
    public String getDisplayName() {
        return citationStyle.getTitle();
    }

    @Override
    public String getText() {
        return citationStyle.getSource();
    }

    public String getFilePath() {
        return citationStyle.getFilePath();
    }

    @Override
    public String getName() {
        return citationStyle.getTitle();
    }

    public CitationStyle getCitationStyle() {
        return citationStyle;
    }
}
