
public class ObjectsReader
{

    private static final String TAG = "ObjectsReader";
    
    private static FlickrPhotoResponse responseObject = null;
   
    public static FlickrPhotoResponse readFlickrPhotoResponse(String jsonFileUrl)
    {
    	Log.d(TAG, "---------------- JSON URL: " + jsonFileUrl);
        InputStream source = retrieveStream(jsonFileUrl);
        
        if(source != null){
        	Log.d(TAG, "InputStream NOT null");
        }
        
        Reader reader = new InputStreamReader(source);

        Log.d(TAG, reader.toString());
        
        Gson gson = new Gson();
        
        Log.d(TAG, "getting TypeToken");
        Type listType = new TypeToken<FlickrPhotoResponse>()
        {
        }.getType();
        
        Log.d(TAG, "listType is: " + listType.toString());

        responseObject = gson.fromJson(reader, listType);
        
        if(responseObject != null){
        	Log.d(TAG, "responseObject is NOT NULL");
        }
        
        return responseObject;
    }



    private static InputStream retrieveStream(String url)
    {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try
        {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK)
            {
                Log.w(TAG, "Error " + statusCode + " for URL " + url);
                return null;
            }
            else{
            	Log.d(TAG, "Status code: " + statusCode);
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        } catch (IOException e)
        {
            getRequest.abort();
            Log.w(TAG, "Error for URL " + url, e);
        }

        return null;

    }
}
