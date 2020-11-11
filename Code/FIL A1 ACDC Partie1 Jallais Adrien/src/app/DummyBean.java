package app;

class DummyBean {
	private String dummy;

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public String getDummy() {
		return dummy;
	}

	public DummyBean(DummyBean another) {
		this.dummy = another.dummy; // you can access
	}

	public DummyBean() {
		this.dummy = "foo";
	}
}