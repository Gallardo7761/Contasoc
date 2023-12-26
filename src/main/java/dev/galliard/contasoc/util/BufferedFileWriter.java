package dev.galliard.contasoc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * User: Eugene Chipachenko
 * Date: 20.09.13
 * Time: 10:21
 */
public class BufferedFileWriter extends OutputStreamWriter
{
  public BufferedFileWriter( String fileName ) throws IOException
  {
    super( new FileOutputStream( fileName ), StandardCharsets.UTF_8);
  }

  public BufferedFileWriter( String fileName, boolean append ) throws IOException
  {
    super( new FileOutputStream( fileName, append ), StandardCharsets.UTF_8);
  }

  public BufferedFileWriter( String fileName, String charsetName, boolean append ) throws IOException
  {
    super( new FileOutputStream( fileName, append ), Charset.forName( charsetName ) );
  }

  public BufferedFileWriter( File file ) throws IOException
  {
    super( new FileOutputStream( file ), StandardCharsets.UTF_8);
  }
}