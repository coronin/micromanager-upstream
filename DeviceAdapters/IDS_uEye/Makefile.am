pkglibdir = $(DEVICEADAPTERPATH)
AM_CXXFLAGS = $(MMDEVAPI_CXXFLAGS) -fpermissive
UEYELIB = -lueye_api
pkglib_LTLIBRARIES = libmmgr_dal_IDS_uEye.la
libmmgr_dal_IDS_uEye_la_SOURCES = IDS_uEye.cpp IDS_uEye.h
libmmgr_dal_IDS_uEye_la_LIBADD = $(MMDEVAPI_LIBADD) $(UEYELIB)
libmmgr_dal_IDS_uEye_la_LDFLAGS = $(MMDEVAPI_LDFLAGS)

EXTRA_DIST = license.txt
