
	private class DataLoadingTask extends AsyncTask<Integer, Integer, Integer>
	{
		@Override
		protected void onPreExecute()
		{
			mProgressBar = new ProgressDialog(PhotosListActivity.this);
		    mProgressBar.setCancelable(false);
		    mProgressBar.setMessage("Downloading Images...");
		    mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		    mProgressBar.setProgress(0);
		    mProgressBar.setMax(100);
		    mProgressBar.show();			
		}

		@Override
		protected Integer doInBackground(Integer... arg0)
		{

			try
			{
				read();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return null;

		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			// Do nothing
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			display();
		}

	}

	protected void read() throws IOException
	{
		String jsonFileUrl = FeaturesConfiguration.getFlickrurl();
		mFlickrPhotoResponse = ObjectsReader.readFlickrPhotoResponse(jsonFileUrl);
		
		mListOfPhotos = mFlickrPhotoResponse.getPhotos().getFlickrPhotoList();
		
		int progress = 0;
		int progressIncrement = mProgressBar.getMax()/mListOfPhotos.size();
		
		if(mListOfPhotos != null){
			Log.d(TAG, mListOfPhotos.get(0).getTitle());
			Log.d(TAG, mListOfPhotos.get(0).getPhotoURL());
		}
		else
			Log.d(TAG, "Null probably");
		
		// Getting whole PhotoURL list and Generating BITMAPs for Thumbnails
		for(int i = 0 ; i < mListOfPhotos.size() ; i++){
			Log.d(TAG, mListOfPhotos.get(i).getPhotoURL());
			
			try{
				URL myFileUrl = new URL (mListOfPhotos.get(i).getPhotoURL(PhotoSize.t));
			    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			    conn.setDoInput(true);
			    conn.connect();
			    mPhotoBitmap.add(BitmapFactory.decodeStream(conn.getInputStream()));
			    mMap.put(mListOfPhotos.get(i), mPhotoBitmap.get(i));
			    
			    // Display Progress
			    progress += progressIncrement;
			    mProgressBar.setProgress(progress);
			}
			catch(MalformedURLException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void display()
	{
		MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.activity_list_row, mListOfPhotos);
		mListView.setAdapter(adapter);		
		mProgressBar.dismiss();
	}
