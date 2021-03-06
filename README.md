Symptom Management Server
=========================
<p>
Coursera Mobile Cloud Computing with Android Capstone REST Spring Server project 2014</p>
<p>
This is the Java Spring Server for the Capstone project course in the Coursera Mobile Cloud Computing with Android (MoCCA) Specialization, which is the first Capstone course offered on the Coursera platform.  The Capstone started the week of Oct 5th, 2014 with the final submission due November 30th, 2014.</p>

<p>The deliverables included this server along with an Android App.  The app source, design document and evaluations for this project can be found at : </p>
<p>https://github.com/dmdavids/SymptomManagementApp</p>
<p>
The Capstone MOOC gives Signature Track students who passed all previous MOOCs in the MoCCA Specialization “with Distinction” an opportunity to integrate and demonstrate the knowledge they acquired across the three earlier content area MOOCs.
</p>
<p>https://www.coursera.org/specialization/mobilecloudcomputing/2?utm_medium=courseDescripLabel</p>
<p>
Course 1 Programming Mobile Applications for Android Handheld Systems (5200 met the "distinction" criteria.)</p>
<p>
Course 2 Pattern-Oriented Software Architectures: Programming Mobile Services for Android Handheld Systems (3,600 users met the "distinction" criteria.)</p>
<p>
Course 3 Programming Cloud Services for Android Handheld Systems
</p>
========================
<p><strong>Project Description:</strong>
Over half of head and neck cancer patients will receive chemotherapy and radiation
therapy for 6-7 weeks. During this time, symptoms can flare suddenly. The worst
symptom for patients is radiation-induced oral pain and sore throat. Between the 2nd
and 4th week of treatment, pain may increase from nothing to severe (i.e., “I’m
swallowing glass”) in a matter of days. Once the pain has escalated to this point,
patients stop eating and drinking and often must undergo a surgical procedure for the
placement of a feeding tube.
Week to week, doctors rely largely on a patient’s memory to intervene and manage
symptoms, but patient recall is notoriously unreliable, and weekly visits can leave
symptoms sub-optimally managed for days.
“Symptom Management” is a smartphone-based application which provides a more
reliable method to assess symptoms in near real-time. It provides patients a simple way
to work with their healthcare providers to improve management of pain and quality of
care.</p>

<p> <strong>This is a demo server project only.</strong> An actual server would have significantly more security and other privacy requirements due to health regulations.  This server is implemented as a local server in the Eclipse environment.  This server implements Spring Security and OAuth2 but the certificates and keystore are not meant to used in production environment.  The database is Mongo DB (see http://docs.mongodb.org/manual/installation/).</p>
<p>
The administrator user is currently hardcoded to admin/pass.  When the admin adds new patients or physicians (POST), credientials are auto-generated using the firstname.lastname and the password is initially "pass".  The app currently does not have functionality to change this but it would possible to do with changes to the API for credentials.
</p>
<p>
<strong>Dependencies for the Server Project:</strong></p>
<p>
Spring Boot</p>
<p>
Mongo DB </p>
<p>
Spring Security</p>
<p>
OAuth2</p>
<p>
Square Retrofit</p>
<p>
JUnit</p>
<p>
As much as I would haved liked to develop a full suite of test cases, unfortunately the amount of work on this project did not allow me enough time to do this in the given time frame. 
</p>
====================================================================================
<strong>Below is a list of the APIs that were implemented for this project.</strong>

<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=633
 style='width:474.95pt;border-collapse:collapse;border:none;mso-border-alt:
 dotted windowtext .5pt;mso-yfti-tbllook:1184;mso-padding-alt:0in 5.4pt 0in 5.4pt;
 mso-border-insideh:.5pt dotted windowtext;mso-border-insidev:.5pt dotted windowtext'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:12.2pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;mso-border-alt:
  dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><b style='mso-bidi-font-weight:normal'>Path<o:p></o:p></b></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border:dotted windowtext 1.0pt;
  border-left:none;mso-border-left-alt:dotted windowtext .5pt;mso-border-alt:
  dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><b style='mso-bidi-font-weight:normal'><span
  style='font-size:12.0pt'>Available Commands<o:p></o:p></span></b></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border:dotted windowtext 1.0pt;
  border-left:none;mso-border-left-alt:dotted windowtext .5pt;mso-border-alt:
  dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><b style='mso-bidi-font-weight:normal'><span
  style='font-size:12.0pt'>Authorized Roles<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1;height:25.0pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='mso-field-code:" HYPERLINK "'><span class=MsoHyperlink><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/patient</span></span></span><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>POST<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:2;height:37.25pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/patient/%7bid%7d"><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/patient/{id}</span></a><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>PUT<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>DELETE<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Patient, Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Patient, Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3;height:12.2pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/patient/find?name="><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/patient/find?name=”first</span></a><span class=MsoHyperlink><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'> last</span></span><span style='font-size:12.0pt'>”<o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:4;height:25.0pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/physician"><span style='font-size:
  12.0pt;color:windowtext;text-decoration:none;text-underline:none'>/physician</span></a><span
  style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>POST<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:5;height:37.25pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/physician/%7bid%7d"><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/physician/{id}</span></a><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'><span
  style='mso-spacerun:yes'> </span>PUT<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>DELETE<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.25pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:6;height:12.75pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:12.75pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/physician/%7bid%7d"><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/physician/{id}</span></a><span style='font-size:12.0pt'>/alert<o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.75pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.75pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:7;height:24.45pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:24.45pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/medication"><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/medication</span></a><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  24.45pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>POST<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  24.45pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Patient, Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:8;height:37.7pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:37.7pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><a href="https://192.168.0.34:8080/medication/%7bid%7d"><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/medication/{id}</span></a><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.7pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>PUT<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>DELETE<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  37.7pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Patient, Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:9;height:12.2pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'>/medication/find?name=”name”</p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Patient, Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:10;height:25.0pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>/alert<o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>POST<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  25.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin<o:p></o:p></span></p>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:11;height:12.2pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='mso-field-code:" HYPERLINK "'><span class=MsoHyperlink><span
  style='font-size:12.0pt;color:windowtext;text-decoration:none;text-underline:
  none'>/alert/{id}</span></span></span><span style='font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>DELETE<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>Admin, Physician<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:12;mso-yfti-lastrow:yes;height:12.2pt'>
  <td width=224 style='width:168.1pt;border:dotted windowtext 1.0pt;border-top:
  none;mso-border-top-alt:dotted windowtext .5pt;mso-border-alt:dotted windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt;height:12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'>/credential/find?username=”username”</p>
  </td>
  <td width=213 valign=top style='width:159.9pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>GET<o:p></o:p></span></p>
  </td>
  <td width=196 valign=top style='width:146.95pt;border-top:none;border-left:
  none;border-bottom:dotted windowtext 1.0pt;border-right:dotted windowtext 1.0pt;
  mso-border-top-alt:dotted windowtext .5pt;mso-border-left-alt:dotted windowtext .5pt;
  mso-border-alt:dotted windowtext .5pt;padding:0in 5.4pt 0in 5.4pt;height:
  12.2pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:12.0pt'>No Roles (Pre-Login usage)<o:p></o:p></span></p>
  </td>
 </tr>
</table>
<p>
LICENSE

    Copyright 2014 Sky Woman Technology LLC
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.
    
  
 
