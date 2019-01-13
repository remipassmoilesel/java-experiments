package org.tpserver.requesthandlers;

import org.tpserver.Connexion;

public class ConsoleDisplayHandler extends AbstractRequestHandler {

	private boolean outputActivated = true;

	@Override
	public boolean isRequestSupported(Connexion t) {
		return true;
	}

	@Override
	public ProcessResponse processConnexion(Connexion t) {

		if (outputActivated) {

			System.out.println();
			System.out.println("Receiving request: " + t);

			// ArrayList<String> lines = t.getStringRequest();
			// for (String l : lines) {
			// System.out.println("\t" + l);
			// }

		}

		return ProcessResponse.REQUEST_FILTERED;

	}

}
