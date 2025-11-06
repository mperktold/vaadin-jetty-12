package com.example;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;

@PageTitle("Download")
@Route("")
public class DownloadView extends Composite<VerticalLayout> {

	public DownloadView() {
		getContent().add(new Anchor(
			DownloadHandler.forClassResource(DownloadView.class, "/Test%Test.txt"),
			"Download"
		));
	}
}
