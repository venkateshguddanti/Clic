package com.clic.org.serve.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemDocs implements Parcelable {

	private String docType;
	private String imageData;
	private String filePath;
	private String status;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.docType);
		dest.writeString(this.imageData);
		dest.writeString(this.filePath);
		dest.writeString(this.status);
	}

	public ItemDocs() {
	}

	protected ItemDocs(Parcel in) {
		this.docType = in.readString();
		this.imageData = in.readString();
		this.filePath = in.readString();
		this.status = in.readString();
	}

	public static final Parcelable.Creator<ItemDocs> CREATOR = new Parcelable.Creator<ItemDocs>() {
		@Override
		public ItemDocs createFromParcel(Parcel source) {
			return new ItemDocs(source);
		}

		@Override
		public ItemDocs[] newArray(int size) {
			return new ItemDocs[size];
		}
	};
}
